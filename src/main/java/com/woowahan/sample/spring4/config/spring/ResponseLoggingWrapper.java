package com.woowahan.sample.spring4.config.spring;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.output.ByteArrayOutputStream;
import org.apache.commons.io.output.TeeOutputStream;

import javax.servlet.ServletOutputStream;
import javax.servlet.WriteListener;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletResponseWrapper;
import java.io.IOException;

/**
 * response log
 */
@Slf4j
class ResponseLoggingWrapper extends HttpServletResponseWrapper {
    private final ByteArrayOutputStream bos = new ByteArrayOutputStream();
    private long id;
    private long startTime;


    public ResponseLoggingWrapper(long requestId, long startTime, HttpServletResponse response) {
        super(response);
        this.id = requestId;
        this.startTime = startTime;
    }

    @Override
    public ServletOutputStream getOutputStream() throws IOException {
        final ServletOutputStream servletOutputStream = ResponseLoggingWrapper.super.getOutputStream();
        return new ServletOutputStream() {
            private TeeOutputStream tee = new TeeOutputStream(servletOutputStream, bos);

            @Override
            public void write(byte[] b) throws IOException {
                tee.write(b);
            }

            @Override
            public void write(byte[] b, int off, int len) throws IOException {
                tee.write(b, off, len);
            }

            @Override
            public void flush() throws IOException {
                tee.flush();
                logRequest();
            }

            @Override
            public void write(int b) throws IOException {
                tee.write(b);
            }

            @Override
            public boolean isReady() {
                return servletOutputStream.isReady();
            }

            @Override
            public void setWriteListener(WriteListener writeListener) {
                servletOutputStream.setWriteListener(writeListener);
            }


            @Override
            public void close() throws IOException {
                super.close();
                // do the logging
                logRequest();
            }
        };
    }

    private void logRequest() {
        byte[] toLog = toByteArray();
        if (toLog != null && toLog.length > 0)
            log.info("id={}, totalTime={}, params={}", getId(), System.currentTimeMillis() - getStartTime(), new String(toLog));
    }

    /**
     * this method will clear the buffer, so
     *
     * @return captured bytes from stream
     */
    private byte[] toByteArray() {
        byte[] ret = bos.toByteArray();
        bos.reset();
        return ret;
    }

    @SuppressWarnings("WeakerAccess")
    long getId() {
        return id;
    }

    @SuppressWarnings("WeakerAccess")
    long getStartTime() {
        return startTime;
    }
}

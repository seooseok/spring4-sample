package com.woowahan.sample.spring4.config.spring;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.input.TeeInputStream;
import org.apache.commons.io.output.ByteArrayOutputStream;

import javax.servlet.ReadListener;
import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import java.io.IOException;

/**
 * request log
 */
@Slf4j
class RequestLoggingWrapper extends HttpServletRequestWrapper {

    private final ByteArrayOutputStream bos = new ByteArrayOutputStream();
    private long id;

    public RequestLoggingWrapper(long requestId, HttpServletRequest request) {
        super(request);
        this.id = requestId;
    }

    @Override
    public ServletInputStream getInputStream() throws IOException {
        final ServletInputStream servletInputStream = RequestLoggingWrapper.super.getInputStream();
        return new ServletInputStream() {
            private TeeInputStream tee = new TeeInputStream(servletInputStream, bos);

            @Override
            public int read() throws IOException {
                return tee.read();
            }

            @Override
            public int read(byte[] b, int off, int len) throws IOException {
                return tee.read(b, off, len);
            }

            @Override
            public int read(byte[] b) throws IOException {
                return tee.read(b);
            }

            @Override
            public boolean isFinished() {
                return servletInputStream.isFinished();
            }

            @Override
            public boolean isReady() {
                return servletInputStream.isReady();
            }

            @Override
            public void setReadListener(ReadListener readListener) {
                servletInputStream.setReadListener(readListener);
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
        log.info("id={}, url={}, method={}, params={}", getId(), getRequestURI(), getMethod(), new String(toByteArray()));
    }

    private byte[] toByteArray() {
        return bos.toByteArray();
    }

    long getId() {
        return id;
    }

    long getStartTime() {
        return System.currentTimeMillis();
    }
}

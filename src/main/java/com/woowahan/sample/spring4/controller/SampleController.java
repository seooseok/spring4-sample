package com.woowahan.sample.spring4.controller;

import com.fasterxml.jackson.annotation.JsonView;
import com.woowahan.sample.spring4.common.constant.ApiStatus;
import com.woowahan.sample.spring4.domain.ApiRes;
import com.woowahan.sample.spring4.domain.JView;
import com.woowahan.sample.spring4.service.SampleService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * 샘플코드
 */
@Api(description = "sample api")
@RestController
@RequestMapping("/hello")
public class SampleController {

	@Autowired
	private SampleService sampleService;

	/**
	 * 간단히 helloworld를 찍어봅니다.
	 * @return
	 */
	@ApiOperation("hello world를 찍어보자.")
	@RequestMapping(value = "/world", method = RequestMethod.GET)
	public ApiRes world(){
		return ApiRes.ok().build();
	}

	/**
	 * 입력을 받아서 바로 output으로 던집니다.
	 * @param q
	 * @return
	 */
	@ApiOperation("q= 로 들어온 값을 api result로 돌려주자.")
	@RequestMapping(value = "/echo", method = RequestMethod.GET)
	public ApiRes echo(@ApiParam(value = "query") @RequestParam String q) {
		return new ApiRes.Builder<String>(ApiStatus.ok, "0000","success", q).build();
	}

	/**
	 * 객체 자체를 output으로 던집니다.
	 * @param name
	 * @param count
	 * @return
	 */
	@ApiOperation("entity 객체를 json으로 출력하는 것을 보자")
	@RequestMapping(value = "/entity", method = RequestMethod.GET)
	public ApiRes entity(@ApiParam(value = "이름이예요") @RequestParam String name
							, @ApiParam(value = "숫자입니다") @RequestParam int count) {

		SampleEntity sampleEntity = new SampleEntity();

		sampleEntity.setName(name);
		sampleEntity.setCount(count);



		return new ApiRes.Builder<SampleEntity>(ApiStatus.ok, "0000", "success", sampleEntity).build();
	}



	@ApiOperation("메모리 db에 저장해보자")
	@RequestMapping(value = "/save", method = RequestMethod.GET)
	public ApiRes useDb(@ApiParam(value = "이름이예요") @RequestParam String name
			, @ApiParam(value = "숫자입니다") @RequestParam int count) {

		sampleService.save(name,count);

		return ApiRes.ok().build();
	}




	protected class SampleEntity {

		@Setter @Getter
		private String name;

		@JsonView
		@Setter @Getter
		private int count;
	}
}

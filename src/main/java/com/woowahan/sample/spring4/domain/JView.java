package com.woowahan.sample.spring4.domain;

/**
 * JsonView Interface에 대한 이해</br>
 * Controller에 @JsonView(View.Default.class) 라고 붙여 놓으면 Domain 객체 안의 value의 @JsonView(View.Default.class)라고 지정된 애들만 보인다. </br>
 * 내부적으로 정의하는 Intraface간에 Extends 함으로서 상속관계를 구성할 수 있고 Controller의 @JsonView를 이용해서 그걸 보여줄 수 있다. </br>
 */
public class JView {
    public interface Res {}
}

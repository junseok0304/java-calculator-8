package calculator;

import camp.nextstep.edu.missionutils.test.NsTest;
import calculator.domain.StringCalculator;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static camp.nextstep.edu.missionutils.test.Assertions.assertSimpleTest;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class ApplicationTest extends NsTest {

    @Nested
    @DisplayName("Application 전체 흐름 테스트")
    class ApplicationFlowTest {
        @Test
        void 커스텀_구분자_사용() {
            assertSimpleTest(() -> {
                run("//;\n1;2");
                assertThat(output()).contains("결과 : 3");
            });
        }

        @Test
        void 여러_글자_커스텀_구분자_사용() {
            assertSimpleTest(() -> {
                run("//[***]\n3***4***5");
                assertThat(output()).contains("결과 : 12");
            });
        }

        @Test
        void 음수_입력시_예외_발생() {
            assertSimpleTest(() ->
                    assertThatThrownBy(() -> runException("-1,2,3"))
                            .isInstanceOf(IllegalArgumentException.class)
            );
        }

        @Test
        void 문자_입력시_예외_발생() {
            assertSimpleTest(() ->
                    assertThatThrownBy(() -> runException("1,a,2"))
                            .isInstanceOf(IllegalArgumentException.class)
            );
        }
    }

    @Nested
    @DisplayName("StringCalculator 단위 테스트")
    class DomainLogicTest {
        private final StringCalculator calculator = new StringCalculator();

        @Test
        void 빈_문자열이면_0_반환() {
            int sum = calculator.add("");
            assertThat(sum).isEqualTo(0);
        }

        @Test
        void 기본_구분자로_합_계산() {
            int sum = calculator.add("1,2:3");
            assertThat(sum).isEqualTo(6);
        }

        @Test
        void 한_글자_커스텀_구분자_사용() {
            // "\\n"을 "\n"으로 수정
            int sum = calculator.add("//;\n1;2;3");
            assertThat(sum).isEqualTo(6);
        }

        @Test
        void 여러_글자_커스텀_구분자_사용() {
            // "\\[***]\\n"을 "[***]\n"으로 수정
            int sum = calculator.add("//[***]\n3***4***5");
            assertThat(sum).isEqualTo(12);
        }

        @Test
        void 구분자_사이_비어있는_값_존재시_예외() {
            assertThatThrownBy(() -> calculator.add("1,2,"))
                    .isInstanceOf(IllegalArgumentException.class);
        }

        @Test
        void 음수_존재시_예외() {
            assertThatThrownBy(() -> calculator.add("1,2,-1"))
                    .isInstanceOf(IllegalArgumentException.class);
        }
    }

    @Override
    protected void runMain() {
        Application.main(new String[]{});
    }
}

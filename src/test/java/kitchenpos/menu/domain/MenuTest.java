package kitchenpos.menu.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertAll;

import java.util.Arrays;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class MenuTest {
    
    @Test
    @DisplayName("메뉴가 잘 생성되는지 확인")
    void 메뉴_생성() {
        // given, when
        Menu 메뉴 = Menu.of("치킨", 17000, MenuGroup.from("메뉴그룹"));
        
        // then
        assertAll(
                () -> assertThat(메뉴.getName()).isEqualTo("치킨"),
                () ->assertThat(메뉴.getPrice()).isEqualTo(Price.from(17000))
        );
    }
    
    @DisplayName("메뉴 가격은 0원 이상이어야한다 - 예외처리")
    @Test
    void 메뉴_가격_0원_이상() {
        // given, when, then
        assertThatThrownBy(() -> {
            Menu.of("치킨", -7000, MenuGroup.from("메뉴그룹"));
        }).isInstanceOf(IllegalArgumentException.class)
        .hasMessage("가격은 0원 이상이어야 합니다");
    }
    
    @DisplayName("메뉴 가격은 포함된 상품들의 총 금액보다 클 수 없다- 예외처리")
    @Test
    void 메뉴_가격_상품_가격_비교() {
        // given
        Menu 메뉴 = Menu.of("치킨세트", 24000, MenuGroup.from("메뉴그룹"));
        Product 치킨 = Product.of("치킨", 18000);
        Product 콜라 = Product.of("치킨", 2000);
        MenuProduct 메뉴_치킨 = MenuProduct.of(치킨, 1L);
        MenuProduct 메뉴_콜라 = MenuProduct.of(콜라, 2L);
        
        // when, then
        assertThatThrownBy(() -> {
            메뉴.addMenuProducts(Arrays.asList(메뉴_치킨, 메뉴_콜라));
        }).isInstanceOf(IllegalArgumentException.class)
        .hasMessage("메뉴 가격이 상품 가격의 합보다 큽니다");
    }

}

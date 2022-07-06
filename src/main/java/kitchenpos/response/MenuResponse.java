package kitchenpos.response;

import java.math.BigDecimal;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import kitchenpos.domain.Menu;

public class MenuResponse {
    private Long id;
    private String name;
    private BigDecimal price;
    private Long menuGroupId;
    private List<MenuProductResponse> menuProducts;

    protected MenuResponse(final Long id, final String name, final BigDecimal price, final Long menuGroupId,
                           final List<MenuProductResponse> menuProducts) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.menuGroupId = menuGroupId;
        this.menuProducts = menuProducts;
    }

    protected MenuResponse() {
    }

    public static List<MenuResponse> of(final List<Menu> menus) {
        return menus.stream()
                .map(MenuResponse::of)
                .collect(Collectors.toList());
    }

    public static MenuResponse of(final Menu menu) {
        return new MenuResponse(menu.getId(), menu.getName(), menu.getPrice(),
                menu.getMenuGroupId(), MenuProductResponse.of(menu.getMenuProducts()));
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public Long getMenuGroupId() {
        return menuGroupId;
    }

    public List<MenuProductResponse> getMenuProducts() {
        return menuProducts;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        MenuResponse that = (MenuResponse) o;
        return Objects.equals(id, that.id) && Objects.equals(name, that.name)
                && Objects.equals(price, that.price) && Objects.equals(menuGroupId, that.menuGroupId)
                && Objects.equals(menuProducts, that.menuProducts);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, price, menuGroupId, menuProducts);
    }
}

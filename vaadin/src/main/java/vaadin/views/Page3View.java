package vaadin.views;

import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

@Route(value = "page3")
@PageTitle("Tryout | Pagina 3")
public class Page3View extends VerticalLayout {

    public Page3View() {
        add(new Label("Pagina 3"));
    }

}

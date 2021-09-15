package vaadin.views;

import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

@Route(value = "page2")
@PageTitle("Tryout | Pagina 2")
public class Page2View extends VerticalLayout {

    public Page2View() {
        add(new Label("Pagina 2"));
    }

}

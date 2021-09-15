package vaadin.views;

import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

@Route(value = "page4")
@PageTitle("Tryout | Pagina 4")
public class Page4View extends VerticalLayout {

    public Page4View() {
        add(new Label("Pagina 4"));
    }

}

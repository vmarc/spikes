package vaadin.views;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.datepicker.DatePicker;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.HighlightConditions;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.router.RouterLink;

@Route
public class MainView extends VerticalLayout {

    public MainView() {

        Component page1 = new Button("Pagina 1", event -> onPage1Clicked());
        Component page2 = new Button("Pagina 2", event -> onPage2Clicked());
        Component page3 = new Button("Pagina 3", event -> onPage3Clicked());

        HorizontalLayout buttons = new HorizontalLayout();
        buttons.add(page1);
        buttons.add(page2);
        buttons.add(page3);

        RouterLink page1Link = new RouterLink("Pagina 1", Page1View.class);
        page1Link.setHighlightCondition(HighlightConditions.sameLocation());
        RouterLink page2Link = new RouterLink("Pagina 2", Page2View.class);
        page2Link.setHighlightCondition(HighlightConditions.sameLocation());
        RouterLink page3Link = new RouterLink("Pagina 3", Page3View.class);
        page3Link.setHighlightCondition(HighlightConditions.sameLocation());
        RouterLink page4Link = new RouterLink("Pagina 4", Page4View.class);
        page4Link.setHighlightCondition(HighlightConditions.sameLocation());

        HorizontalLayout links = new HorizontalLayout();
        links.add(page1Link);
        links.add(page2Link);
        links.add(page3Link);
        links.add(page4Link);

        VerticalLayout rows = new VerticalLayout();
        rows.add(buttons);
        rows.add(links);
        rows.add(new DatePicker("Geboorte datum"));

        add(rows);
    }

    private void onPage1Clicked() {
        Notification.show("Page 1 clicked");
    }

    private void onPage2Clicked() {
        Notification.show("Page 2 clicked");
    }

    private void onPage3Clicked() {
        Notification.show("Page 3 clicked");
    }

}

package vaadin.views;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.value.ValueChangeMode;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import vaadin.domain.Person;

@Route(value = "page1")
@PageTitle("Tryout | Pagina 1")
public class Page1View extends VerticalLayout {

    private final Grid<Person> grid = new Grid<>(Person.class);
    private final TextField filterText = new TextField();

    public Page1View() {
        addClassName("list-view");
        setSizeFull();
        configureGrid();

        add(getToolbar(), grid);
    }

    private void configureGrid() {
        grid.addClassNames("contact-grid");
        grid.setSizeFull();
        grid.addColumn(Person::getLastName).setHeader("Familienaam");
        grid.addColumn(Person::getFirstName).setHeader("Voornaam");
        // grid.getColumns().forEach(col -> col.setAutoWidth(true));
    }

    private HorizontalLayout getToolbar() {
        filterText.setPlaceholder("Filter op naam...");
        filterText.setClearButtonVisible(true);
        filterText.setValueChangeMode(ValueChangeMode.LAZY);

        Button addButton = new Button("Persoon toevoegen");

        HorizontalLayout toolbar = new HorizontalLayout(filterText, addButton);
        toolbar.addClassName("toolbar");
        return toolbar;
    }

}

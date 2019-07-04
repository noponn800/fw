package com.rafal.family;

import com.rafal.family.model.ApplicationTask;
import com.rafal.family.repositories.TaskRepository;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.select.Select;
import com.vaadin.flow.component.textfield.TextArea;
import com.vaadin.flow.data.binder.Binder;
import com.vaadin.flow.data.binder.ValidationException;
import com.vaadin.flow.router.Route;

import java.util.Collection;
import java.util.List;
import java.util.stream.IntStream;


@Route("main")
public class MainApplicationView extends VerticalLayout {

//    @Autowired
    private TaskRepository taskRepository;

    HorizontalLayout horizontal = new HorizontalLayout();
    VerticalLayout userVertical = new VerticalLayout();
    VerticalLayout taskVertical1 = new VerticalLayout();
    VerticalLayout rankingVertical = new VerticalLayout();
    Button logout = new Button("Log out");
    Button addTask = new Button("Add task");
    TextArea taskName = new TextArea("Task name");
    TextArea taskBody = new TextArea("Task description");
    List<Integer> priorities = (List<Integer>) IntStream.range(0,11);
    Select<Integer> taskPriority = new Select<>();
    Label taskLabel = new Label("Tasks");
    Label userLabel = new Label("User");
    Label rankingLabel = new Label("Team ranking");
    Grid<ApplicationTask> taskGrid = new Grid<>(ApplicationTask.class);
    Binder<ApplicationTask> taskBinder = new Binder<>(ApplicationTask.class);

    public MainApplicationView(TaskRepository taskRepository)
    {
        taskPriority.setItems(priorities);
        this.taskRepository = taskRepository;

        //only to test out the grid
//        ApplicationTask task = new ApplicationTask("This is an example task.", "This is the task's body.");
//        taskRepository.save(task);

        add(buildApplicationLayout());
    }


    

    public HorizontalLayout buildApplicationLayout() {

        // Define fields
        rankingLabel.setWidth("100px");
        logout.setWidth("100px");
        logout.setHeight("100px");
        taskName.setMaxLength(100);
        taskName.setWidth("500px");
        taskBody.setMaxLength(500);
        taskBody.setWidth("500px");
        horizontal.add(userVertical, taskVertical1, rankingVertical);
        taskVertical1.add(taskLabel);
        rankingVertical.add(rankingLabel);
        userVertical.add(userLabel);
        taskVertical1.add(taskName);
        taskVertical1.add(taskBody);
        taskVertical1.add(addTask);
        taskVertical1.add(taskGrid);
        //setting grid items
        taskGrid.setItems((Collection<ApplicationTask>) taskRepository.findAll());
        taskGrid.getColumnByKey("id").setVisible(false);
        taskGrid.getColumnByKey("group").setVisible(false);
        userVertical.add(logout);

        // Define bindings
        taskBinder.forField(taskBody).bind(ApplicationTask::getBody, ApplicationTask::setBody);
        taskBinder.forField(taskName).bind(ApplicationTask::getTitle, ApplicationTask::setTitle);
        taskBinder.forField(taskPriority).bind(ApplicationTask::getPriority, ApplicationTask::setPriority);

        //Save button
        addTask.addClickListener(e -> {
            try {
                ApplicationTask savedTask = new ApplicationTask();
                Notification.show("Task added: name: " + taskName.getValue() + " desription: " + taskBody.getValue());
                taskBinder.writeBean(savedTask);
                taskRepository.save(savedTask);
                taskGrid.setItems((Collection<ApplicationTask>) taskRepository.findAll());
            } catch(ValidationException exception)
            {
                Notification.show("Ooops looks like the code doesn't work now");

    }
}
        );
        return horizontal;
    }
}


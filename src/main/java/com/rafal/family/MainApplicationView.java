package com.rafal.family;

import com.rafal.family.model.ApplicationTask;
import com.rafal.family.model.ApplicationUser;
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

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.IntStream;


@Route("main")
public class MainApplicationView extends VerticalLayout {

//    @Autowired
    private TaskRepository taskRepository;

    HorizontalLayout horizontal = new HorizontalLayout();
    HorizontalLayout taskHorizontal1 = new HorizontalLayout();
    HorizontalLayout taskHorizontal2 = new HorizontalLayout();
    VerticalLayout userVertical = new VerticalLayout();
    VerticalLayout taskVertical1 = new VerticalLayout();
    VerticalLayout rankingVertical = new VerticalLayout();
    Button logout = new Button("Log out");
    Button addTask = new Button("Add task");
    Button editTask = new Button("Edit task");
    TextArea taskName = new TextArea("Task name");
    TextArea taskBody = new TextArea("Task description");
    Select<Integer> taskPriority = new Select<Integer>();
    Select<Integer> taskPoints = new Select<Integer>();
    Label taskLabel = new Label("Tasks");
    Label userLabel = new Label("User");
    Label rankingLabel = new Label("Team ranking");
    Grid<ApplicationTask> taskGrid = new Grid<>(ApplicationTask.class);
    Grid<ApplicationUser> userGrid = new Grid<>(ApplicationUser.class);
    Binder<ApplicationTask> taskBinder = new Binder<>(ApplicationTask.class);

    public MainApplicationView(TaskRepository taskRepository)
    {
        List<Integer> priorities = new ArrayList<Integer>();
        List<Integer> points = new ArrayList<>();
        for(int i = 0; i <= 10; i++)
        {
            priorities.add(i);
        }
        for(int i = 0; i <= 100; i++)
        {
            points.add(i);
        }
        taskPriority.setItems(priorities);
        taskPoints.setItems(points);
        taskPriority.setLabel("Task Priority");
        taskPoints.setLabel("Task Points");
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
        rankingVertical.add(userGrid);
        userVertical.add(userLabel);
        taskVertical1.add(taskName);
        taskVertical1.add(taskBody);
        taskHorizontal1.add(taskPriority);
        taskHorizontal1.add(taskPoints);
        taskHorizontal2.add(addTask);
        taskHorizontal2.add(editTask);
        taskVertical1.add(taskHorizontal1);
        taskVertical1.add(taskHorizontal2);
        taskVertical1.add(taskGrid);
        //setting grid items
        taskGrid.setItems((Collection<ApplicationTask>) taskRepository.findAll());
        taskGrid.getColumnByKey("id").setVisible(false);
        taskGrid.getColumnByKey("group").setVisible(false);
        taskGrid.setSelectionMode(Grid.SelectionMode.NONE);
        taskGrid.addItemClickListener(event ->taskBinder.setBean(event.getItem()));
        userGrid.getColumnByKey("password").setVisible(false);
        userGrid.getColumnByKey("groups").setVisible(false);
        userGrid.getColumnByKey("id").setVisible(false);
        userGrid.getColumnByKey("username").setVisible(false);
        userGrid.setWidth("500px");
        userVertical.add(logout);

        // Define bindings
        taskBinder.forField(taskBody).bind(ApplicationTask::getBody, ApplicationTask::setBody);
        taskBinder.forField(taskName).bind(ApplicationTask::getTitle, ApplicationTask::setTitle);
        taskBinder.forField(taskPriority).bind(ApplicationTask::getPriority, ApplicationTask::setPriority);
        taskBinder.forField(taskPoints).bind(ApplicationTask::getPoints, ApplicationTask::setPoints);

        //Save button
        addTask.addClickListener(e -> {
            saveTask(new ApplicationTask());
}
        );
        editTask.addClickListener(c -> {
            saveTask(taskBinder.getBean());
        });
        return horizontal;
    }

    private void saveTask(ApplicationTask applicationTask) {
        try {
            ApplicationTask saveTask = applicationTask;
            Notification.show("Task added: name: " + taskName.getValue() + " desription: " + taskBody.getValue());
            taskBinder.writeBean(saveTask);
            taskRepository.save(saveTask);
            taskGrid.setItems((Collection<ApplicationTask>) taskRepository.findAll());
        }catch(ValidationException ve)
        {
            Notification.show("Failed to add new task.");
        }
    }
}


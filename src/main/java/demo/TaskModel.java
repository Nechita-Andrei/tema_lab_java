package demo;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.UUID;

@XmlRootElement
public class TaskModel implements BaseModel {

    public enum TaskStatus {
        OPEN,
        IN_PROGRESS,
        CLOSED
    }
    public enum TaskSeverity {
        LOW,
        NORMAL,
        HIGH
    }
    public enum ImportFormat{
        CSV,
        XML
    }
    private String id;
    private String title;
    private String description;
    private String assignedTo;
    private TaskStatus status;
    private TaskSeverity severity;

    public TaskModel() {
        id = UUID.randomUUID().toString();
    }

    @XmlAttribute
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @XmlAttribute
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @XmlAttribute
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @XmlAttribute
    public String getAssignedTo() {
        return assignedTo;
    }

    public void setAssignedTo(String assignedTo) {
        this.assignedTo = assignedTo;
    }

    @XmlAttribute
    public TaskStatus getStatus() {
        return status;
    }

    public void setStatus(TaskStatus status) {
        this.status = status;
    }

    @XmlAttribute
    public TaskSeverity getSeverity() {
        return severity;
    }

    public void setSeverity(TaskSeverity severity) {
        this.severity = severity;
    }

    public void update (TaskModel task) {
        if(task != null) {
            title = task.getTitle();
            description = task.getDescription();
            assignedTo = task.getAssignedTo();
            severity = task.getSeverity();
            status = task.getStatus();
        }
    }

    public void patch (TaskModel task) {
        if(task != null) {
            if (task.getTitle() != null) {
                title = task.getTitle();
            }
            if (task.getDescription() != null) {
                description = task.getDescription();
            }
            if (task.getAssignedTo() != null) {
                assignedTo = task.getAssignedTo();
            }
            if (task.getSeverity() != null) {
                severity = task.getSeverity();
            }
            if (task.getStatus() != null) {
                status = task.getStatus();
            }
        }
    }
}

package UI.Screen.AddIssue;

import Core.Manager.IssueManager;
import Core.Model.Issue;
import Core.Model.Priority;
import Core.Model.Status;
import Core.Model.Type;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class AddIssueController {
    private IssueManager issueManager;

    public AddIssueController() {
        this.issueManager = IssueManager.getInstance();
    }

    public Issue addIssue(String title, String description, Status status,
                          Type type, Priority priority, ArrayList<String> tags) {
        Issue issue = new Issue(title, description, status, type, priority, tags);
        issueManager.addIssue(issue);
        return issue;
    }

}

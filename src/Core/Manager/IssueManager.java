package Core.Manager;

import Core.DataBase.IssueDatabase;
import Core.Model.Issue;
import Core.Model.Project;

import java.util.ArrayList;

public class IssueManager {
    private static IssueManager instance; // Singleton instance
    private IssueDatabase issueDatabase;
    private ArrayList<Issue> issues;

    private IssueManager() {
        this.issueDatabase = IssueDatabase.getInstance(); // Utilize the existing singleton instance
        this.issues = new ArrayList<>();
    }

    public static IssueManager getInstance() {
        if (instance == null) {
            instance = new IssueManager();
        }
        return instance;
    }

    public ArrayList<Issue> getAllIssues() {
        return issueDatabase.getAllIssues();
    }

    public void addIssue(Issue issue) {
        issueDatabase.addIssue(issue);
    }

    public void editIssue(int index, Issue editedIssue) {
        issueDatabase.editIssue(index, editedIssue);
    }

    public void removeIssue(int index) {
        issueDatabase.removeIssue(index);
    }

    public Issue getIssueByTitle(String title) {
        for (Issue issue : issues) {
            if (issue.getTitle().equals(title)) {
                return issue;
            }
        }
        return null;
    }
}

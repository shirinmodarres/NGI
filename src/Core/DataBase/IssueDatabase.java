package Core.DataBase;

import Core.Model.Issue;

import java.util.ArrayList;

public class IssueDatabase {
    private static IssueDatabase instance;
    private ArrayList<Issue> issues;

    private IssueDatabase() {
        issues = new ArrayList<>();
    }

    public static IssueDatabase getInstance() {
        if (instance == null) {
            instance = new IssueDatabase();
        }
        return instance;
    }

    public ArrayList<Issue> getAllIssues() {
        return issues;
    }

    public void addIssue(Issue issue) {
        issues.add(issue);
    }

    public void editIssue(int index, Issue editedIssue) {
        issues.set(index, editedIssue);
    }

    public void removeIssue(int index) {
        issues.remove(index);
    }
}



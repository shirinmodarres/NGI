package Core.Manager;

import Core.DataBase.IssueDatabase;
import Core.Model.Issue;
import Core.Model.Project;

import java.util.ArrayList;

public class IssueManager {
    static  private ArrayList<Issue> issues;
    IssueDatabase issueDatabase=IssueDatabase.getInstance();

public IssueManager (IssueDatabase issueDatabase){
    this.issueDatabase=issueDatabase;
    this.issues=new ArrayList<>();

    }
    public IssueManager() {
        issueDatabase = IssueDatabase.getInstance();
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




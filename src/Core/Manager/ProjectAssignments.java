package Core.Manager;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class ProjectAssignments {

        private Map<String, ArrayList<String>> userToProjectsMap;

        public ProjectAssignments() {
            userToProjectsMap = new HashMap<>();
        }

        public void assignProjectToUser(String userName, String projectName) {
            userToProjectsMap.computeIfAbsent(userName, k -> new ArrayList<>()).add(projectName);
        }

        public void unassignProjectFromUser(String userName, String projectName) {
            ArrayList<String> projects = userToProjectsMap.get(userName);
            if (projects != null) {
                projects.remove(projectName);
                if (projects.isEmpty()) {
                    userToProjectsMap.remove(userName);
                }
            }
        }

        public ArrayList<String> getAssignedProjectsForUser(String userName) {
            return userToProjectsMap.getOrDefault(userName, new ArrayList<>());
        }

        public void printAssignments() {
            for (Map.Entry<String, ArrayList<String>> entry : userToProjectsMap.entrySet()) {
                String userName = entry.getKey();
                ArrayList<String> projects = entry.getValue();
                System.out.println(userName + " is assigned to projects: " + projects);
            }
        }
    }



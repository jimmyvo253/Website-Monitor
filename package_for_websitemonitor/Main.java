// package package_for_websitemonitor;

// import package_for_websitemonitor.controller.MonitorController;
// import package_for_websitemonitor.model.User;

// public class Main {
//     public static void main(String[] args) {
//     //    try {
//     //     NotificationService notificationService = new NotificationService();
//     //     WebsMonitor monitor = new WebsMonitor(notificationService);

//     //     User user1 = new User("user1@example.com", "email");
//     //     monitor.registerWebsite(user1, "https://www.wikipedia.org/", "daily");

//     //     monitor.startMonitoring();

//     // } catch (Exception e) {
//     //     System.err.println("Fatal error: " + e.getMessage());
//     //     e.printStackTrace();
//     // }
//     // }
//     try {
//        MonitorController controller = new MonitorController();

//         // Set comparison strategy (can be changed at runtime)
//         controller.setComparisonStrategy("text"); // Options: "size", "text", or "exact"

//         User user = new User("test@example.com", "email");
//         controller.registerUserWebsite(user, "https://campuas.frankfurt-university.de/pluginfile.php/616131/mod_resource/content/6/SWED_2025_05.pdf", "daily");
//         controller.startMonitoring();

//     } catch (Exception e) {
//         System.err.println("Fatal error: " + e.getMessage());
//         e.printStackTrace();
//         }
//     }
//     }


// package package_for_websitemonitor;

// import package_for_websitemonitor.controller.MonitorController;
// import package_for_websitemonitor.model.User;

// public class Main {
//     public static void main(String[] args) {
//         try {
//             if (args.length < 1) {
//                 System.out.println("Usage: java package_for_websitemonitor.Main <website_url>");
//                 return;
//             }

//             String website = args[0];  // get URL from command line
//             System.out.println("Monitoring: " + website);

//             MonitorController controller = new MonitorController();

//             // You can optionally pass comparison strategy via args[1]
//             controller.setComparisonStrategy("text");

//             User user = new User("test@example.com", "email");
//             controller.registerUserWebsite(user, website, "daily");
//             controller.startMonitoring();

//         } catch (Exception e) {
//             System.err.println("Fatal error: " + e.getMessage());
//             e.printStackTrace();
//         }
//     }
// }

package package_for_websitemonitor;
import package_for_websitemonitor.controller.MonitorController;
import package_for_websitemonitor.model.User;
public class Main {
    public static void main(String[] args) {
        try {
            if (args.length < 1) {
                System.out.println("Usage: java package_for_websitemonitor.Main <website_url> [comparison_strategy]");
                return;
            }

            String website = args[0];
            String strategy = (args.length > 1) ? args[1] : "text"; // Default is "text"

            System.out.println("Monitoring: " + website);
            System.out.println("Using comparison strategy: " + strategy);

            MonitorController controller = new MonitorController();
            controller.setComparisonStrategy(strategy);

            User user = new User("test@example.com", "email");
            controller.registerUserWebsite(user, website, "daily");
            controller.startMonitoring();

        } catch (Exception e) {
            System.err.println("Fatal error: " + e.getMessage());
            e.printStackTrace();
        }
    }
}

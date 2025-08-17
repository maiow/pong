//
// Created by mivanovskaya on 17.08.2025.
//

import UIKit
import ComposeApp

@UIApplicationMain
class AppDelegate: UIResponder, UIApplicationDelegate {
    var window: UIWindow?
    func application(
        _ application: UIApplication,
        didFinishLaunchingWithOptions launchOptions: [UIApplication.LaunchOptionsKey: Any]? = nil
    ) -> Bool {
        window = UIWindow(frame: UIScreen.main.bounds)
        window?.rootViewController = ComposeViewController { ComposeContent() }
        window?.makeKeyAndVisible()
        return true
    }
}

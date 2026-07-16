import Foundation
import UIKit

@objc(MapstedCordovaPlugin)
class MapstedCordovaPlugin: CDVPlugin {
    var showPropertyList: Bool = true

    override init() {
        super.init()
        // Initialize any properties here
        showPropertyList = true
    }

    override func pluginInitialize() {
        // Called when the plugin is initialized
    }

    @objc(launchMapActivity:)
    func launchMapActivity(command: CDVInvokedUrlCommand) {
        if !showPropertyList {
            // Instantiate the view controller from the Swift storyboard
            let storyboard = UIStoryboard(name: "MapstedCordovaPlugin", bundle: Bundle(for: MapstedCordovaPlugin.self))
            guard let viewController = storyboard.instantiateViewController(withIdentifier: "MAPSTEDVC") as? UIViewController else {
                print("Error: Failed to instantiate view controller.")
                return
            }

            // Set the modal presentation style and present the view controller
            viewController.modalPresentationStyle = .overCurrentContext

            var topViewController = UIApplication.shared.keyWindow?.rootViewController
            while let presentedViewController = topViewController?.presentedViewController {
                topViewController = presentedViewController
            }

            topViewController?.present(viewController, animated: true, completion: nil)
        } else {
            let storyboard = UIStoryboard(name: "MapstedCordovaPlugin", bundle: Bundle(for: MapstedCordovaPluginPropListViewController.self))
            guard let viewController = storyboard.instantiateViewController(withIdentifier: "MAPSTEDPROPLISTVC") as? UIViewController else {
                print("Error: Failed to instantiate view controller.")
                return
            }

            // Set the modal presentation style and present the view controller
            viewController.modalPresentationStyle = .overCurrentContext

            var topViewController = UIApplication.shared.keyWindow?.rootViewController
            while let presentedViewController = topViewController?.presentedViewController {
                topViewController = presentedViewController
            }

            let navController = UINavigationController(rootViewController: viewController)
            navController.modalPresentationStyle = .overCurrentContext
            topViewController?.present(navController, animated: true, completion: nil)
        }
    }

}

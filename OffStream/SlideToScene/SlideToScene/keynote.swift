//
//  keynote.swift
//  SlideToScene
//
//  Created by Paulo Pereira on 05/07/2021.
//

import Foundation

func openKeynoteDocument(pathToFile: String) {
    let scriptSource = """
        tell application "Keynote"
            activate
            open "%@"
        end tell
        """
    
    let script = NSAppleScript(source: String(format: scriptSource, pathToFile))
    script?.executeAndReturnError(nil)
}

func readCurrentSlideNotes() -> String? {
    let scriptSource = """
        tell application "Keynote"
            set currentPresentation to get front document
            set slideNotes to get presenter notes of (get current slide of currentPresentation)
            return slideNotes
        end tell
        """
    
    let script = NSAppleScript(source: scriptSource)
    return script?.executeAndReturnError(nil).stringValue
}


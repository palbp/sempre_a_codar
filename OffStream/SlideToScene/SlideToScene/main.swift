//
//  main.swift
//  SlideToScene
//
//  Created by Paulo Pereira on 04/07/2021.
//

import Foundation

func startup(presentation: String) -> URLSessionWebSocketTask? {
    openKeynoteDocument(pathToFile: presentation)
    return openOBS()
}

func run(obsWebSocket: URLSessionWebSocketTask) {
    
    obsWebSocket.resume()
    
    var currentNotes: String? = nil
    while true {
        let readNotes = readCurrentSlideNotes()
        
        if readNotes == nil {
            print("Presentation was closed. Terminating.")
            exit(0)
        }
        
        if let slideNotes = readNotes {
            if !slideNotes.isEmpty && slideNotes != currentNotes {
                setCurrentScene(webSocket: obsWebSocket, sceneName: slideNotes)
                currentNotes = slideNotes
            }
        }
        
        Thread.sleep(forTimeInterval: 0.25)
    }
}

if CommandLine.arguments.count != 1 {
    print("Starting up Keynote and OBS...")
    if let obsSession = startup(presentation: CommandLine.arguments[1]) {
        run(obsWebSocket: obsSession)
    }
    else {
        print("Could not connect to OBS. Terminating.")
        exit(0)
    }
} else {
    print("Please provide a keynote document")
}


//
//  obs.swift
//  SlideToScene
//
//  Created by Paulo Pereira on 05/07/2021.
//

import Foundation

let OBS_WS_URL = "ws://localhost:4444"

func openOBS() -> URLSessionWebSocketTask? {
    let scriptSource = """
        tell application "OBS"
            activate
        end tell
        """
    
    let script = NSAppleScript(source: scriptSource)
    script?.executeAndReturnError(nil)
    
    let urlSession = URLSession(configuration: .default)
    if let url = URL(string: OBS_WS_URL) {
        return urlSession.webSocketTask(with: url)
    }
    
    return nil
}

// A request contains at least
// request-type: String     -> String name of the request type
// message-id: String       -> Client defined identifier for the message, will be echoed in the response

// SetCurrentScene request
// scene-name: String       -> Name of the scene to switch to

// A response contains at least
// message-id: String       -> The client defined identifier specified in the request
// status: String           -> Response status, will be one of the following: ok, error
// error: String            -> (Optional) An error message accompanying an error status

// For details:
// https://github.com/Palakis/obs-websocket/blob/4.x-current/docs/generated/protocol.md#obs-websocket-491-protocol-reference

func setCurrentScene(webSocket: URLSessionWebSocketTask, sceneName: String) {
    let message = URLSessionWebSocketTask.Message.string("""
    {
        \"request-type\": \"SetCurrentScene\",
        \"message-id\": \"1\", \"scene-name\":
        \"\(sceneName)\"
    
    }
    """)
    webSocket.send(message) { error in
        if let error = error {
            print("WebSocket sending error: \(error)")
        }
    }
}

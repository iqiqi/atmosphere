/*
* Copyright 2012 Jeanfrancois Arcand
*
* Licensed under the Apache License, Version 2.0 (the "License"); you may not
* use this file except in compliance with the License. You may obtain a copy of
* the License at
*
* http://www.apache.org/licenses/LICENSE-2.0
*
* Unless required by applicable law or agreed to in writing, software
* distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
* WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
* License for the specific language governing permissions and limitations under
* the License.
*/
package org.atmosphere.websocket;

import org.atmosphere.cpr.AtmosphereConfig;
import org.atmosphere.cpr.AtmosphereRequest;

import java.util.List;

/**
 * A WebSocket based protocol implementation. Implement this class to process WebSockets messages and dispatch it to
 * Atmosphere or any consumer of WebSocket message.
 *
 * @author Jeanfrancois Arcand
 */
public interface WebSocketProtocol {

    /**
     * Allow an implementation to query the AtmosphereConfig of init-param, etc.
     *
     * @param config {@link org.atmosphere.cpr.AtmosphereConfig}
     */
    void configure(AtmosphereConfig config);

    /**
     * Parse the WebSocket message, and delegate the processing to the {@link org.atmosphere.cpr.AtmosphereFramework#asyncSupport} or
     * to any existing technology. Invoking  {@link org.atmosphere.cpr.AtmosphereFramework#asyncSupport} will delegate the request processing
     * to the {@link org.atmosphere.cpr.AtmosphereHandler} implementation. Returning null means this implementation will
     * handle itself the processing/dispatching of the WebSocket's request;
     * <br>
     * As an example, this is how Websocket messages are delegated to the
     * Jersey runtime.
     * <br>
     *
     * @param webSocket The {@link WebSocket} connection
     * @param data      The Websocket message
     * @return A list of {@link org.atmosphere.cpr.AtmosphereResource}, or null if the request won't be dispatched.
     */
    List<AtmosphereRequest> onMessage(WebSocket webSocket, String data);

    /**
     * Parse the WebSocket message, and delegate the processing to the {@link org.atmosphere.cpr.AtmosphereFramework#asyncSupport} or
     * to any existing technology. Invoking  {@link org.atmosphere.cpr.AtmosphereFramework#asyncSupport} will delegate the request processing
     * to the {@link org.atmosphere.cpr.AtmosphereHandler} implementation. Returning null means this implementation will
     * handle itself the processing/dispatching of the WebSocket's request;
     * <br>
     * As an example, this is how Websocket messages are delegated to the
     * Jersey runtime.
     * <br>
     *
     * @param webSocket The {@link WebSocket} connection
     * @param offset    offset message index
     * @param length    length of the message.
     * @return A list of {@link org.atmosphere.cpr.AtmosphereResource}, or null if the request won't be dispatched.
     */
    List<AtmosphereRequest> onMessage(WebSocket webSocket, byte[] data, int offset, int length);

    /**
     * Invoked when a WebSocket is opened
     * @param webSocket {@link WebSocket}
     */
    void onOpen(WebSocket webSocket);

    /**
     * Invoked when a WebSocket is closed
     * @param webSocket {@link WebSocket}
     */
    void onClose(WebSocket webSocket);

    /**
     * Invoked when an error occurs.
     * @param webSocket {@link WebSocket}
     * @param t a {@link org.atmosphere.websocket.WebSocketProcessor.WebSocketException}
     */
    void onError(WebSocket webSocket, WebSocketProcessor.WebSocketException t);

}
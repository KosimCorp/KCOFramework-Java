/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.kosim.kcoframework.view;

import java.util.Stack;
import java.util.function.Supplier;
import java.util.stream.Stream;
import org.kosim.kcoframework.component.BuildContext;
import org.kosim.kcoframework.component.KComponent;

/**
 *
 * @author raz
 * @param <S>
 * @param <T>
 */
public class KNavigator<S extends KNavigator<S, T>, T> extends KViewChain<S, T> {
    private Route[] routes;
    
    private String initialRoute;
    private Route selectedRoute;
    
    private final NavigatorState navigatorState = new NavigatorState(this);
    
    public static NavigatorState of(BuildContext context) {
        return context.inheritedParentAndGet(KNavigator.class).navigatorState;
    }
    
    public S routes(Route... routes) {
        this.routes = routes;
        
        return (S) this;
    }
    
    public S initialRoute(String initialRoute) {
        this.initialRoute = initialRoute;
        
        return (S) this;
    }

    @Override
    public KComponent build() {
        if (selectedRoute == null) {
            selectedRoute = Stream.of(routes).filter(route -> initialRoute.startsWith(route.getPath())).findFirst().get();
        }
        
        return new KBuilder<>()
                .child(selectedRoute.getBuilder());
    }
    
    public static class NavigatorState {
        private KNavigator navigator;
        
        private Stack<Object> stack = new Stack<>();
        
        private NavigatorState(KNavigator navigator) {
            this.navigator = navigator;
        }
        
        public void push(String path) {
            navigator.setState(() -> {
                navigator.selectedRoute = Stream.of(navigator.routes).filter(route -> path.startsWith(route.getPath())).findFirst().get();
            });
            
            navigator.waitForBuild(element -> {
                stack.add(element);
            }, true);
        }
        
        public void pushReplacement(String path) {
            stack.pop();
            
            navigator.setState(() -> {
                navigator.selectedRoute = Stream.of(navigator.routes).filter(route -> path.startsWith(route.getPath())).findFirst().get();
            });
        }
    }
    
    public static class Route {
        private String path;
        private Supplier<KComponent> builder;

        public Route() {}
        
        public Route(String path, Supplier<KComponent> builder) {
            this.path = path;
            this.builder = builder;
        }

        public String getPath() {
            return path;
        }

        public void setPath(String path) {
            this.path = path;
        }

        public Supplier<KComponent> getBuilder() {
            return builder;
        }

        public void setBuilder(Supplier<KComponent> builder) {
            this.builder = builder;
        }
    }
}

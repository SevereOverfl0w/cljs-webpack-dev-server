let npmDeps = require('./target/public/cljs-out/app/npm_deps.js').npmDeps;
require('./target/public/cljs-out/app/main.js');
require('coolfoo');

if (module.hot) {

    let triggered_reload = false;
    document.addEventListener("figwheel.after-load", (e) =>{
        if (triggered_reload) {
            triggered_reload = false;
            io.dominic.webpack_dev_server.app.dev.hard_reload();
        };
    });

    let reload_npm = () => {
        npmDeps.coolfoo = require('coolfoo');
        triggered_reload = true;
        figwheel.core.reload_namespaces(['io.dominic.webpack_dev_server.app']);
    };

    module.hot.accept('./target/public/cljs-out/app/npm_deps.js', reload_npm);
    module.hot.accept('./target/public/cljs-out/app/main.js', reload_npm);
    module.hot.accept('coolfoo', reload_npm);
}

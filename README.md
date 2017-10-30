A demonstration of writing a Node.js [Express](https://expressjs.com/) app using ClojureScript.

This repo contains the same tiny hello world app, set up with each of the major ClojureScript compilation tools.

## Standalone

This uses [cljs.jar](https://github.com/clojure/clojurescript/releases/latest) and a custom build script as described in the [ClojureScript Quick-Start](https://clojurescript.org/guides/quick-start).

It also includes a small wrapper shell script to download ClojureScript if it's not there already, and run the build.

```
cd standalone
./build.sh
node npmdemo.js
```

## Figwheel

Using [lein-figwheel](https://github.com/bhauman/lein-figwheel). This is the most mature and complete tool out there, and is generally what I recommend using. The code looks slightly different here because it needs to be reloadable.

This version gives you hot code reloading, a ClojureScript REPL, and the possibility to connect with nREPL (e.g. with Emacs/CIDER).

### Dev mode:

```
cd figwheel
lein figwheel
```

In a separate terminal

```
node npmdemo-dev.js
```

### Production build:

```
lein cljsbuild once prod
node npmdemo.js
```

## [Shadow-cljs](https://github.com/thheller/shadow-cljs)

The new kid on the block. Meant to make configuration easier. Should feel more familiar when coming from Node.

```
cd shadow-cljs
npm install
node_modules/.bin/shadow-cljs compile app
node npmdemo.js
```

## Boot

Added for completeness.

```
cd boot
boot build
node target/npmdemo.js
```

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
```

### Dev mode:
```
# compile once
npx shadow-cljs compile app
# watch + reload + REPL
npx shadow-cljs watch app
node npmdemo.js

# to actually connect to the REPL (while node process is running)
npx shadow-cljs cljs-repl app
```

### Production build:
```
npx shadow-cljs release app
node npmdemo.js
```

## Boot

Advanced build tool. Can also offer live-reloading, a ClojureScript repl and an nREPL server. Unfortunately live-reloading is not yet implemented for Node. (see [adzerk-oss/boot-reload #68](https://github.com/adzerk-oss/boot-reload/issues/68))

Start the dev compilation and watcher

```
cd boot
boot dev
```

In another terminal

```
cd target
node npmdemo.js
```

For a production build

```
boot build
```

## Lumo

Lumo is not a compilation tool (although it has experimental support for use as
a compiler), instead it's a ClojureScript runtime based on Node.js. You can
think of it as Node+ClojureScript bundled into a single executable. This makes
it behave more like other interpreted languages, i.e. you can just launch it
with a script without having to do a separate compilation step.

It will find dependencies in `node_modules`, so you can use it with `npm`/`yarn`.

```
cd lumo
npx lumo-cljs src/npmdemo/core.cljs
```

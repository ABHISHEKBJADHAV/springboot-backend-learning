Create an workplace with Library architecture - ng new my-workspace --no-create-application

Generate libraries - ng generate library <lib-name>

Build libraries (one-time) - ng build <lib-name>

Build libraries (watch mode) - ng build <lib-name> --watch



* angular.json :- workspace build configuration
* package.json :- deps + scripts; also library package metadata in dist/
* tsconfig\*.json :- path mappings and compilation modes

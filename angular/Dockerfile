FROM node:14-bullseye

RUN apt-get update && apt-get install build-essential python3 make g++ python3-pip -y && \
    npm install --global npm@latest && \
    npm install --global node-gyp@latest && \
    npm config set node_gyp $(npm prefix -g)/lib/node_modules/node-gyp/bin/node-gyp.js && \
    python3 --version && \
    python3 -m pip --version && \
    mkdir /usr/app2

WORKDIR /usr/app2
CMD cp -Rf /usr/app/* /usr/app2/ && ls -la /usr/app2 && \
npm i --save-dev typescript@4.5.4 && npm i && npm run build && \
rm -rf /usr/app/dist && mkdir /usr/app/dist && cp -Rf /usr/app2/dist/* /usr/app/dist

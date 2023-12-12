const express = require("express");
const http2 = require("http2");
const http2Express = require("http2-express-bridge");
const { readFileSync } = require("fs");

const app = http2Express(express);

app.get("/express", (req, res) => {
  res.send("hello World");
});

const options = {
  key: readFileSync("/home/sugan/localhost-key.pem"),
  cert: readFileSync("/home/sugan/localhost.pem"),
  allowHTTP1: true,
};
const server = http2.createSecureServer(options, app);
server.listen(3001);

console.log("Hello world");

var Vertx = require("vertx-js/vertx");
var vertx = Vertx.vertx({
	"workerPoolSize": 40
});
var server = vertx.createNetServer();
server.connectHandler(function(socket) {
	console.log("set connect handler");
});
server.listen(1234, "localhost", function(resp) {
	console.log("got it");
});

console.log("vertx initialized");

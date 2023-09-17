<!DOCTYPE html>
<html>
<head>
    <title>WebSocket Client</title>
</head>
<body>
    <h1>WebSocket Client</h1>

    <div id="output"></div>

    <script>
        // WebSocket 서버의 주소를 여기에 입력합니다.
        const serverUrl = "ws://localhost:8090/WebSocketMonitoring/";

        // WebSocket 연결을 생성합니다.
        const socket = new WebSocket(serverUrl);

        // 연결이 열렸을 때 호출되는 이벤트 핸들러
        socket.onopen = (event) => {
            console.log("WebSocket 연결이 열렸습니다.");
        };

        // 서버로부터 메시지를 받았을 때 호출되는 이벤트 핸들러
        socket.onmessage = (event) => {
            const message = event.data;
            console.log("서버로부터 받은 메시지:", message);
            // 받은 메시지를 웹 페이지에 표시하기 위해 출력 div에 추가
            document.getElementById("output").innerHTML += "<p>" + message + "</p>";
        };

        // 연결이 닫혔을 때 호출되는 이벤트 핸들러
        socket.onclose = (event) => {
            if (event.wasClean) {
                console.log("WebSocket 연결이 정상적으로 닫혔습니다.");
            } else {
                console.error("WebSocket 연결이 비정상적으로 닫혔습니다.");
            }
        };

        // 에러가 발생했을 때 호출되는 이벤트 핸들러
        socket.onerror = (error) => {
            console.error("WebSocket 에러:", error);
        };
    </script>
</body>
</html>

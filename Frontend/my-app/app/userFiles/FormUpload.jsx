'use client';

import { useState } from "react";

export default function FormUpload() {

    const [file, setFile] = useState(null);
    const [username, setUsername] = useState("");
    const [timeLength, setTimeLength] = useState("");

    async function handleUpload() {
        if (!file) {
          alert("Выберите файл");
          return;
        }

        const formData = new FormData();
        formData.append("file", file);
        formData.append("username", username);
        formData.append("timeLength", timeLength);

        const response = await fetch("http://localhost:8080/api/upload", {
          method: "POST",
          body: formData,
        });

        if (!response.ok) {
          alert("Ошибка загрузки");
          return;
        }

        const data = await response.json();
        console.log("Ответ сервера:", data);
        alert("Файл загружен");
      }

    return (
        <div>
          <input type="file" onChange={(e) => setFile(e.target.files[0])} />

          <input
            type="text"
            placeholder="Имя пользователя"
            value={username}
            onChange={(e) => setUsername(e.target.value)}
          />

          <input
            type="number"
            placeholder="Минуты хранения"
            value={timeLength}
            onChange={(e) => setTimeLength(e.target.value)}
          />

          <button onClick={handleUpload}>Загрузить</button>
        </div>
      );
    }
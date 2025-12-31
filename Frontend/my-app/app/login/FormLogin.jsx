'use client';

import { useState } from "react";

export default function FormLogin() {

    const [username, setUsername] = useState("");
    const [password, setPassword] = useState("");

    async function handleLogin() {
        if (!username) {
            alert("Введите логин");
            return;
        }

        if (!password) {
            alert("Введите пароль")
            return;
        }

        const formData = new FormData();
        formData.append("username", username);
        formData.append("password", password);

        const response = await fetch("http://localhost:8080/api/login", {
            method: "POST",
            body: formData,
            credentials: "include"
        });

        if (response.ok) {
            alert("Вход выполнен");
            //здесь можно редирект например на профиль
        }
        else {
            const bodyText = await response.text();
            alert(bodyText);
        }
      }

    return (
        <div>
          <input
            type="text"
            placeholder="Имя пользователя"
            value={username}
            onChange={(e) => setUsername(e.target.value)}
          />

          <input
            type="password"
            placeholder="Пароль"
            value={password}
            onChange={(e) => setPassword(e.target.value)}
          />

          <button onClick={handleLogin}>Войти</button>
        </div>
      );
    }
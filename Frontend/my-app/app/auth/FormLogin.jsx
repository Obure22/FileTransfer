'use client';

import { useState } from "react";

export default function FormLogin() {

    const [email, setEmail] = useState("");
    const [password, setPassword] = useState("");

    async function handleLogin() {
        if (!email) {
          alert("Введите почту");
          return;
        }

        const formData = new FormData();
        formData.append("email", email);
        formData.append("password", password);

        const response = await fetch("http://localhost:8080/api/users", {
          method: "POST",
          body: formData,
        });

        const data = await response.json();
        console.log("Ответ сервера:", data);
        alert("Файл загружен");
      }

    return (
        <div>
          <input
            type="email"
            placeholder="Адрес почты"
            value={email}
            onChange={(e) => setEmail(e.target.value)}
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
'use client';

import { useState } from "react";

export default function FormRegister() {

    const [username, setUsername] = useState("");
    const [email, setEmail] = useState("");
    const [password, setPassword] = useState("");

    async function handleRegister() {
        if (!username) {
            alert("Введите логин");
            return;
        }

        if (!email){
            alert("Введите почту")
            return;
        }

        if (!password){
            alert("Введите пароль")
            return;
        }

        const formData = new FormData();
        formData.append("username", username);
        formData.append("email", email);
        formData.append("password", password);

        const response = await fetch("http://localhost:8080/api/users/register", {
            method: "POST",
            body: formData,
            credentials: "include"
        });
        const bodyText = await response.text();
        alert(bodyText);

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
                type="text"
                placeholder="Почта"
                value={email}
                onChange={(e) => setEmail(e.target.value)}
            />

            <input
                type="password"
                placeholder="Пароль"
                value={password}
                onChange={(e) => setPassword(e.target.value)}
            />

            <button onClick={handleRegister}>Зарегистрироваться</button>
        </div>
    );
}
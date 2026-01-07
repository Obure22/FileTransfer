'use client';

import {useRef, useState} from "react";

export default function FormUpload({fetchWhenUploaded}) {

    const [file, setFile] = useState(null);
    const [timeLength, setTimeLength] = useState("");
    const fileInputRef = useRef(null);

    async function handleUpload() {
        if (!file) {
          alert("Выберите файл");
          return;
        }
        if (!timeLength || timeLength === "0") {
            alert("Выберите время жизни файла")
            return;
        }

        const formData = new FormData();
        formData.append("file", file);
        formData.append("timeLength", timeLength);



        const response = await fetch("http://localhost:8080/api/upload", {
            method: "POST",
            body: formData,
            credentials: "include"
        });

        const data = await response.text();

        if (response.ok){
            fetchWhenUploaded();
            setFile(null);
            setTimeLength("");
            fileInputRef.current.value = "";
        }
        alert(data);
    }

    return (
        <div>
          <input type="file" ref={fileInputRef} onChange={(e) => setFile(e.target.files[0])} />

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
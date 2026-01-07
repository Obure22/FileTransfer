'use client';

import { useState } from "react";
import { useEffect } from 'react';

export default function FilesList() {

    const [files, setFiles] = useState([]);

    useEffect( () => {

             fetch('http://localhost:8080/api/files/user/12',
                {
                    method: "GET",
                    credentials: "include"
                })
                .then(res => res.json())
                .then(data => {
                    setFiles(data);
                    console.log(data)
                });
        }, []
    );
    return(
        <ul>
            {files.map((file) => (
                    <li key={file.id}>
                        <h3>{file.filename}</h3>
                        <p>{file.size}</p>
                        <p>{file.deleteAt}</p>
                    </li>
                )
            )}
        </ul>
    );
}
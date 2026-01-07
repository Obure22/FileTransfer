'use client';

import {useEffect, useState} from "react";
import FilesList from "./FilesList.jsx";
import FormUpload from "./FormUpload.jsx";

export default function FileManager() {
    const [files, setFiles] = useState([]);

    async function fetchFiles(){
        fetch('http://localhost:8080/api/files/user',
            {
                method: "GET",
                credentials: "include"
            })
            .then(res => res.json())
            .then(data => {
                setFiles(data);
                console.log(data);
            });
    }

    useEffect(
        ()=>{
            fetchFiles();
        },
        []
    )

    return(
        <div>
            <FormUpload fetchWhenUploaded={fetchFiles}/>
            <FilesList files={files}/>
        </div>
    );
}
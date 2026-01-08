'use client';

import {useEffect, useState} from "react";
import FilesList from "./FilesList.jsx";
import FormUpload from "./FormUpload.jsx";
import saveAs from 'file-saver';

export default function FileManager() {
    const [files, setFiles] = useState([]);

    async function fetchFiles(){
        await fetch(`http://localhost:8080/api/files/user`,
            {
                method: "GET",
                credentials: "include"
            })
            .then(res => res.json())
            .then(data => {
                setFiles(data);
            });
    }

    async function handleDownload(fileId,fileName){
        await fetch(`http://localhost:8080/api/files/${fileId}`,
            {
                method: "GET",
                credentials: "include"
            })
            .then(res => res.blob())
            .then(data => {
                saveAs(data,fileName)
            });
    }

    async function handleDelete(fileId){
        const response = await fetch(`http://localhost:8080/api/files/${fileId}`,
            {
                method: "DELETE",
                credentials: "include"
            })
        if (response.ok){
            await fetchFiles();
        }
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
            <FilesList files={files} downloadIfClicked={handleDownload} deleteIfClicked={handleDelete}/>
        </div>
    );
}
'use client';

export default function FilesList({files,downloadIfClicked,deleteIfClicked}) {
    return(
        <ul>
            {files.map((file) => (
                    <li key={file.id}>
                        <h3>{file.filename}</h3>
                        <p>{file.size}</p>
                        <p>{file.deleteAt}</p>
                        <button>Изменить</button>
                        <button onClick={()=>deleteIfClicked(file.id)}>Удалить</button>
                        <button>Поделиться</button>
                        <button onClick={()=>downloadIfClicked(file.id,file.filename)}>Скачать</button>
                    </li>
                )
            )}
        </ul>
    );
}
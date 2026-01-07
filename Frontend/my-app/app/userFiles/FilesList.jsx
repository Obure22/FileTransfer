'use client';

export default function FilesList({files}) {
    return(
        <ul>
            {files.map((file) => (
                    <li key={file.id}>
                        <h3>{file.filename}</h3>
                        <p>{file.size}</p>
                        <p>{file.deleteAt}</p>
                        <button>Изменить</button>
                        <button>Удалить</button>
                        <button>Поделиться</button>
                        <button>Скачать</button>
                    </li>
                )
            )}
        </ul>
    );
}
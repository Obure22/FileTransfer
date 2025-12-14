import '@/app/ui/file.css';


export default function FilePage() {
    return (
        <div>
            <main>
                <div className="actions">
                    <h1>Отчет_по_проекту.pdf</h1>
                    <p>Размер: 2.3 МБ</p>
                    <p>Дата загрузки: 2025-11-20</p>
                    <button>Переименовать</button>
                    <button>Удалить</button>
                    <button>Поделиться</button>
                </div>
            </main>
        </div>
    );
}

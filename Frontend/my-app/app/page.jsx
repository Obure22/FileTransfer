import '@/app/ui/files.css';
import FormUpload from './form-upload.jsx';

export default function FilesPage() {
  return (
    <div>
          <header>
              <h1>Мои файлы</h1>
              <FormUpload />


              <div className="profile-button">
                  <button>Профиль</button>
              </div>
          </header>
          <main>
              <ul></ul>
          </main>
          <footer>
              <p>© 2025 Свэн-обменник</p>
          </footer>
    </div>
  );
}

import '@/app/ui/files.css';
import FormUpload from './FormUpload.jsx';

export const metadata = {
    title: 'Файлы пользователя',
    description: 'Это страница файлов пользователя'
};

export default function FilesPage() {
  return (
    <div>
          <main>
              <FormUpload />
              <ul></ul>
          </main>
    </div>
  );
}

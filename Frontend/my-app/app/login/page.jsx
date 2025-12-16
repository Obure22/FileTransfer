import '@/app/ui/auth.css';
import FormLogin from "./FormLogin";

export const metadata = {
    title: 'Авторизация',
    description: 'Это страница авторизации пользователя'
};

export default function AuthPage() {

  return (
    <div>
          <main>
              <FormLogin></FormLogin>
          </main>
    </div>
  );
}

import ReactDOM from 'react-dom/client'
import { RouterProvider, createBrowserRouter } from 'react-router-dom';
import Home from './pages/client/home';
import Login from './pages/auth/login';
import Register from './pages/auth/register';
import Admin from './pages/admin/admin-page';
import AdminDashboard from './pages/admin/dashboard';
import AdminManagers from './pages/admin/managers';
import AdminDoctors from './pages/admin/doctors';
import AdminStaff from './pages/admin/staff';
import AdminUsers from './pages/admin/users';
import BookingCheckupForm from './pages/client/booking';


import { GoogleOAuthProvider } from '@react-oauth/google';


const router = createBrowserRouter([
  {
    path: '/',
    element: <Home />
  },
  {
    path: '/login',
    element: <Login />
  },
  {
    path: '/register',
    element: <Register />
  },
  {
    path: '/booking',
    element: <BookingCheckupForm />
  },
  {
    path: '/admin',
    element: <Admin />,
    children: [
      {
        index: true,
        element: <AdminDashboard />
      },
      {
        path: '/admin/managers',
        element: <AdminManagers />,
      },
      {
        path: '/admin/doctors',
        element: <AdminDoctors />,
      },
      {
        path: '/admin/staff',
        element: <AdminStaff />,
      },
      {
        path: '/admin/users',
        element: <AdminUsers />
      }
    ]
  },

])


ReactDOM.createRoot(document.getElementById('root')).render(
  <GoogleOAuthProvider clientId="115076786122-q76et2blbn1k1dmfpd6d5ss1t192ljj6.apps.googleusercontent.com">
    <RouterProvider router={router} />
  </GoogleOAuthProvider>
)

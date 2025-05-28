import React, { useState } from 'react';
import '@ant-design/v5-patch-for-react-19';
import { Form, Input, Button, Alert, Card, Typography, Divider } from 'antd';
import { useGoogleLogin } from '@react-oauth/google';
import { GoogleOutlined } from '@ant-design/icons';
import axios from 'axios';
import { useNavigate } from 'react-router-dom';

const { Link, Text } = Typography;

const Login = () => {
        const [username, setUsername] = useState('');
        const [password, setPassword] = useState('');
        const [error, setError] = useState('');
        const navigate = useNavigate();
      const handleLogin = async () => {
         try {  
             const response = await axios.post('/api/auth/login', {
                 username,
                 password,
             });
             setError('Success');
             if (response.data.token) {
                   localStorage.setItem('user', JSON.stringify(response.data));
             }
             navigate('/dashboard');
             return response.data;
         } catch (err) {
             setError('Invalid credentials');
         }
    };

    const login = useGoogleLogin({
        onSuccess: codeResponse => console.log(codeResponse),
        flow: 'auth-code',
    });

    return (

        <div style={{ maxWidth: 500, margin: '40px auto', padding: 24, boxShadow: '0 4px 12px rgba(0,0,0,0.15)', borderRadius: 8 }}>
            <h2 style={{ textAlign: 'center', marginBottom: 24 }}>Đăng nhập</h2>
            {error && <Alert message={error} type="error" style={{ marginBottom: 16 }} />}
            <Form
                name="loginForm"
                onFinish={handleLogin}
                layout="vertical"
            >
                <Form.Item
                    label="Tên đăng nhập"
                    name="username"
                    id="username"
                    value={username}
                    onChange={(e) => setUsername(e.target.value)}
                    rules={[{ required: true, message: 'Hãy nhập tên đăng nhập của bạn' }]}
                >
                    <Input placeholder="Tên đăng nhập" />
                </Form.Item>

                <Form.Item
                    label="Mật khẩu"
                    id="password"
                    name="password"
                    value={password}
                    onChange={(e) => setPassword(e.target.value)}
                    rules={[{ required: true, message: 'Hãy nhập mật khẩu của bạn' }]}
                >
                    <Input.Password placeholder="Mật khẩu" />
                </Form.Item>

                <Form.Item>
                    <Button type="primary" htmlType="submit" block>
                        Đăng nhập
                    </Button>
                </Form.Item>
                <div style={{ textAlign: 'center' }}>
                    <Divider style={{ borderColor: 'black' }} >
                        <Text style={{ fontSize: '15px' }}>Chưa có tài khoản? </Text>
                        <Link href="/register" style={{ fontSize: '15px' }}>Đăng kí ngay</Link>
                    </Divider>
                </div>
                <div style={{ textAlign: 'center', paddingBottom: '15px' }}>
                    <Text style={{ fontSize: '13px', color: 'gray' }}>Hoặc</Text>
                </div>

                <div style={{ textAlign: 'center' }}>

                    <Button onClick={() => login()}><GoogleOutlined />Đăng nhập với Google</Button>
                </div>

            </Form>
        </div >
    );
};

export default Login;


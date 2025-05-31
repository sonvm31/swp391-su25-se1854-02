import React from 'react';
import { Breadcrumb, Layout, Menu, theme } from 'antd';
import AppHeader from '../../components/AppHeader';
import AppFooter from '../../components/AppFooter';
const { Header, Content, Footer } = Layout;

const Home = () => {
    const {
        token: { colorBgContainer, borderRadiusLG },
    } = theme.useToken();
    return (
        <Layout>
            <AppHeader />
            <Content style={{ padding: '15px 20px' }}>
                <div
                    style={{
                        background: colorBgContainer,
                        minHeight: 1080,
                        padding: 24,
                        borderRadius: borderRadiusLG,
                    }}
                >
                    Content
                </div>
            </Content>
            <AppFooter />
        </Layout>
    );
};
export default Home;
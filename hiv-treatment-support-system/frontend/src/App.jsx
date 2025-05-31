import { useState } from 'react'
import React from 'react';
import { Form, Input, Select, DatePicker, TimePicker, Divider, Typography } from 'antd';
import './App.css'

const { Title, Text } = Typography;
const { Option } = Select;

function App() {
  const [count, setCount] = useState(0)
  const [form] = Form.useForm();

  // Mock data for services and doctors
  const services = ['Khám tổng quát', 'Khám chuyên khoa', 'Xét nghiệm', 'Chẩn đoán hình ảnh'];
  const doctors = ['BS. Nguyễn Văn B', 'BS. Trần Thị C', 'BS. Lê Văn D'];

  return (
    <div style={{ maxWidth: 800, margin: '0 auto', padding: 24 }}>
      <Title level={3}>Thông tin đặt lịch</Title>
      <Text type="secondary">Vui lòng điền đầy đủ thông tin để đặt lịch khám</Text>

      <Divider />

      <Form
        form={form}
        layout="vertical"
        style={{ marginTop: 24 }}
      >
        <Title level={4}>Họ và tên</Title>
        <Form.Item
          name="name"
          rules={[{ required: true, message: 'Vui lòng nhập họ và tên' }]}
        >
          <Input placeholder="Nguyễn Văn A" size="large" />
        </Form.Item>

        <Form.Item
          name="phone"
          label="Số điện thoại"
          rules={[
            { required: true, message: 'Vui lòng nhập số điện thoại' },
            { pattern: /^[0-9]+$/, message: 'Số điện thoại không hợp lệ' }
          ]}
        >
          <Input placeholder="0912345678" size="large" />
        </Form.Item>

        <Divider />

        <Title level={4}>Loại dịch vụ</Title>
        <Form.Item
          name="service"
          rules={[{ required: true, message: 'Vui lòng chọn loại dịch vụ' }]}
        >
          <Select placeholder="Chọn loại dịch vụ" size="large">
            {services.map(service => (
              <Option key={service} value={service}>{service}</Option>
            ))}
          </Select>
        </Form.Item>

        <Form.Item
          name="doctor"
          label="Bác sĩ"
          rules={[{ required: true, message: 'Vui lòng chọn bác sĩ' }]}
        >
          <Select placeholder="Chọn bác sĩ" size="large">
            {doctors.map(doctor => (
              <Option key={doctor} value={doctor}>{doctor}</Option>
            ))}
          </Select>
        </Form.Item>

        <Form.Item
          name="date"
          label="Ngày khám"
          rules={[{ required: true, message: 'Vui lòng chọn ngày khám' }]}
        >
          <DatePicker
            placeholder="Chọn ngày"
            size="large"
            style={{ width: '100%' }}
            format="DD/MM/YYYY"
          />
        </Form.Item>

        <Form.Item
          name="time"
          label="Giờ khám"
          rules={[{ required: true, message: 'Vui lòng chọn giờ khám' }]}
        >
          <TimePicker
            placeholder="Chọn giờ"
            size="large"
            style={{ width: '100%' }}
            format="HH:mm"
          />
        </Form.Item>

        <Form.Item>
          <button
            type="primary"
            htmlType="submit"
            style={{
              background: '#1890ff',
              color: 'white',
              border: 'none',
              padding: '10px 24px',
              borderRadius: 4,
              cursor: 'pointer',
              fontSize: 16,
              width: '100%'
            }}
          >
            Đặt lịch ngay
          </button>
        </Form.Item>
      </Form>
    </div>
  )
}

export default App

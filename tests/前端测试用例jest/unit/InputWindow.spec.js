import { mount } from '@vue/test-utils';
import InputWindow from '@/components/InputWindow.vue';
import EmojiButton from '@/components/inputWindowComponent/EmojiButton.vue';
import TextInput from '@/components/inputWindowComponent/TextInput.vue';
import FileUploadButton from '@/components/inputWindowComponent/FileUploadButton.vue';
import ChessButton from '@/components/inputWindowComponent/ChessButton.vue';
import ElementPlus from 'element-plus'; // 引入 ElementPlus

describe('InputWindow.vue', () => {
  it('renders correctly', () => {
    const loginInfo = { username: 'testUser' };
    const wrapper = mount(InputWindow, {
      global: {
        plugins: [ElementPlus], // 添加 ElementPlus 插件
        components: {
          EmojiButton,
          TextInput,
          FileUploadButton,
          ChessButton
        },
        stubs: {
            'router-link': true, // Stub router-link to avoid the resolution warning
        },
        provide: {
            'login-info': loginInfo
        }
      }
    });

    expect(wrapper.findComponent(EmojiButton).exists()).toBe(true);
    expect(wrapper.findComponent(TextInput).exists()).toBe(true);
    expect(wrapper.findComponent(FileUploadButton).exists()).toBe(true);
    expect(wrapper.findComponent(ChessButton).exists()).toBe(true);
  });

  it('handles send text event', () => {
    const loginInfo = { username: 'testUser' };
    const wrapper = mount(InputWindow, {
      global: {
        plugins: [ElementPlus], // 添加 ElementPlus 插件
        components: {
          EmojiButton,
          TextInput,
          FileUploadButton,
          ChessButton
        },
        stubs: {
            'router-link': true, // Stub router-link to avoid the resolution warning
        },
        provide: {
            'login-info': loginInfo
        }
      }
    });

    const textInput = wrapper.findComponent(TextInput);
    textInput.vm.$emit('send-text', 'Hello');

    expect(wrapper.emitted('sendtext')).toBeTruthy();
    expect(wrapper.emitted('sendtext')[0][0]).toBe('Hello');
  });

  it('handles upload file event', () => {
    const loginInfo = { username: 'testUser' };
    const wrapper = mount(InputWindow, {
      global: {
        plugins: [ElementPlus], // 添加 ElementPlus 插件
        components: {
          EmojiButton,
          TextInput,
          FileUploadButton,
          ChessButton
        },
        stubs: {
            'router-link': true, // Stub router-link to avoid the resolution warning
        },
        provide: {
            'login-info': loginInfo
        }
      }
    });

    const fileUploadButton = wrapper.findComponent(FileUploadButton);
    fileUploadButton.vm.$emit('upload-file', 'file.txt');

    expect(wrapper.emitted('send-file')).toBeTruthy();
    expect(wrapper.emitted('send-file')[0][0]).toBe('file.txt');
  });

  it('handles send emoji event', () => {
    const loginInfo = { username: 'testUser' };
    const wrapper = mount(InputWindow, {
      global: {
        plugins: [ElementPlus], // 添加 ElementPlus 插件
        components: {
          EmojiButton,
          TextInput,
          FileUploadButton,
          ChessButton
        },
        stubs: {
            'router-link': true, // Stub router-link to avoid the resolution warning
        },
        provide: {
            'login-info': loginInfo
        }
      }
    });

    const emojiButton = wrapper.findComponent(EmojiButton);
    emojiButton.vm.$emit('send-emoji', '😊');

    expect(wrapper.vm.emojiMessage).toBe('😊');
  });

  it('handles start chess event', () => {
    const loginInfo = { username: 'testUser' };
    const wrapper = mount(InputWindow, {
      global: {
        plugins: [ElementPlus], // 添加 ElementPlus 插件
        components: {
          EmojiButton,
          TextInput,
          FileUploadButton,
          ChessButton
        },
        stubs: {
            'router-link': true, // Stub router-link to avoid the resolution warning
        },
        provide: {
            'login-info': loginInfo
        }
      }
    });

    const chessButton = wrapper.findComponent(ChessButton);
    chessButton.vm.$emit('start-chess', 'chess');
  });

  it('clears emoji message', () => {
    const loginInfo = { username: 'testUser' };
    const wrapper = mount(InputWindow, {
      global: {
        plugins: [ElementPlus], // 添加 ElementPlus 插件
        components: {
          EmojiButton,
          TextInput,
          FileUploadButton,
          ChessButton
        },
        stubs: {
            'router-link': true, // Stub router-link to avoid the resolution warning
        },
        provide: {
            'login-info': loginInfo
        }
      }
    });

    wrapper.vm.emojiMessage = '😊';
    wrapper.vm.clearEmojiMessage();

    expect(wrapper.vm.emojiMessage).toBe('');
  });
});
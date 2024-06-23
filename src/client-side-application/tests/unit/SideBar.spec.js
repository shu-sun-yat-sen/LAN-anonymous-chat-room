import { shallowMount } from '@vue/test-utils';
import SideBar from '@/components/SideBar.vue';
import RoomWindow from '@/components/SideBarComponent/RoomWindow.vue';
import { nextTick } from 'vue';

describe('SideBar.vue', () => {
  let wrapper;

  const roomInfo = {
    value: {
      roomList: [
        {
          roomName: 'Room1',
          messages: [{ content: 'Hello' }],
          roomAvatar: 'avatar1.jpg'
        },
        {
          roomName: 'Room2',
          messages: [{ content: 'Hi' }],
          roomAvatar: 'avatar2.jpg'
        }
      ]
    }
  };

  beforeEach(() => {
    wrapper = shallowMount(SideBar, {
      global: {
        provide: {
          'room-info': roomInfo
        }
      }
    });
  });

  it('renders a list of RoomWindow components', () => {
    const roomWindows = wrapper.findAllComponents(RoomWindow);
    expect(roomWindows.length).toBe(roomInfo.value.roomList.length);
  });

  it('passes the correct props to RoomWindow components', () => {
    const roomWindows = wrapper.findAllComponents(RoomWindow);
    roomWindows.forEach((roomWindow, index) => {
      expect(roomWindow.props().roomname).toBe(roomInfo.value.roomList[index].roomName);
      expect(roomWindow.props().newestMessage).toBe(roomInfo.value.roomList[index].messages[0].content);
      expect(roomWindow.props().roomAvatar).toBe(roomInfo.value.roomList[index].roomAvatar);
    });
  });

  it('emits "random-update-room-avatar" event when RoomWindow emits it', async () => {
    const roomWindow = wrapper.findComponent(RoomWindow);
    await roomWindow.vm.$emit('random-update-room-avatar', 'Room1');
    await nextTick();
    expect(wrapper.emitted()['random-update-room-avatar']).toBeTruthy();
    expect(wrapper.emitted()['random-update-room-avatar'][0]).toEqual(['Room1']);
  });
});
